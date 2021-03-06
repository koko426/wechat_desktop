package com.rc.frames;

import com.rc.components.*;
import com.rc.panels.*;
import com.rc.utils.FontUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by song on 07/06/2017.
 */
public class SystemConfigDialog extends JDialog
{
    private static SystemConfigDialog context;
    private JPanel buttonPanel;
    //private JButton cancelButton;
    private JButton okButton;

    private JPanel settingPanel;
    private JPanel settingMenuPanel;
    private JPanel settingAreaPanel;
    private JLabel changeAvatarLabel;
    private JLabel changePasswordLabel;
    private JLabel meLabel;
    private JLabel aboutLabel;
    private JLabel clearCacheLabel;

    private ChangeAvatarPanel changeAvatarPanel;
    private ChangePasswordPanel changePasswordPanel;
    private MePanel mePanel;
    private AboutPanel aboutPanel;
    private ClearCachePanel clearCachePanel;


    private JLabel selectedLabel;

    public static final String CHANGE_AVATAR = "CHANGE_AVATAR";
    public static final String CHANGE_PASSWORD = "CHANGE_PASSWORD";
    public static final String ME = "ME";
    public static final String ABOUT = "ABOUT";
    public static final String CLEAR_CHACE = "CLEAR_CHACE";

    private CardLayout cardLayout = new CardLayout();


    public static final int DIALOG_WIDTH = 580;
    public static final int DIALOG_HEIGHT = 500;
    private Cursor handCursor;


    public SystemConfigDialog(Frame owner, boolean modal)
    {
        super(owner, modal);
        context = this;

        initComponents();
        initData();

        initView();
        setListeners();
    }

    private void initData()
    {

    }


    private void initComponents()
    {
        int posX = MainFrame.getContext().getX();
        int posY = MainFrame.getContext().getY();

        posX = posX + (MainFrame.getContext().currentWindowWidth - DIALOG_WIDTH) / 2;
        posY = posY + (MainFrame.getContext().currentWindowHeight - DIALOG_HEIGHT) / 2;
        setBounds(posX, posY, DIALOG_WIDTH, DIALOG_HEIGHT);
        setUndecorated(true);

        getRootPane().setBorder(new LineBorder(Colors.DIALOG_BORDER));

        // ?????????
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        /*cancelButton = new RCButton("??????");
        cancelButton.setForeground(Colors.FONT_BLACK);*/
        okButton = new RCButton("??????", Colors.MAIN_COLOR, Colors.MAIN_COLOR_DARKER, Colors.MAIN_COLOR_DARKER);
        okButton.setPreferredSize(new Dimension(75, 30));

        // ????????????
        settingPanel = new JPanel();

        settingMenuPanel = new JPanel();
        settingAreaPanel = new JPanel();
        settingAreaPanel.setBorder(new RCBorder(RCBorder.LEFT, Colors.SCROLL_BAR_TRACK_LIGHT));


        handCursor = new Cursor(Cursor.HAND_CURSOR);

        // ??????????????????
        changeAvatarLabel = new JLabel("????????????");
        processButtonLabel(changeAvatarLabel);

        // ??????????????????
        changePasswordLabel = new JLabel("????????????");
        processButtonLabel(changePasswordLabel);

        // "???" ??????
        meLabel = new JLabel("???");
        processButtonLabel(meLabel);

        // ?????? ??????
        aboutLabel = new JLabel("??????");
        processButtonLabel(aboutLabel);

        // ???????????? ??????
        clearCacheLabel = new JLabel("????????????");
        processButtonLabel(clearCacheLabel);


        // ??????????????????
        changeAvatarPanel = new ChangeAvatarPanel();

        // ??????????????????
        changePasswordPanel = new ChangePasswordPanel();

        // "???" ??????
        mePanel = new MePanel();

        // ????????????
        aboutPanel = new AboutPanel();

        // ??????????????????
        clearCachePanel = new ClearCachePanel();

    }


    private void initView()
    {
        //buttonPanel.add(cancelButton, new GBC(0, 0).setWeight(1, 1).setInsets(15, 0, 0, 0));
        buttonPanel.add(okButton, new GBC(1, 0).setWeight(1, 1));

        settingPanel.setLayout(new GridBagLayout());
        settingPanel.add(settingMenuPanel, new GBC(0, 0).setWeight(1, 1).setFill(GBC.BOTH).setInsets(10,0,0,0));
        settingPanel.add(settingAreaPanel, new GBC(1, 0).setWeight(6, 1).setFill(GBC.BOTH).setInsets(10,0,0,0));

        settingMenuPanel.setLayout(new VerticalFlowLayout(VerticalFlowLayout.TOP, 0, 0, true, false));
        settingMenuPanel.add(meLabel);
        settingMenuPanel.add(changeAvatarLabel);
        settingMenuPanel.add(changePasswordLabel);
        settingMenuPanel.add(clearCacheLabel);
        settingMenuPanel.add(aboutLabel);

        settingAreaPanel.setLayout(cardLayout);
        settingAreaPanel.add(mePanel, ME);
        settingAreaPanel.add(changeAvatarPanel, CHANGE_AVATAR);
        settingAreaPanel.add(changePasswordPanel, CHANGE_PASSWORD);
        settingAreaPanel.add(aboutPanel, ABOUT);
        settingAreaPanel.add(clearCachePanel, CLEAR_CHACE);


        add(settingPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        selectedLabel(meLabel);
    }

    private void setListeners()
    {
        okButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                setVisible(false);

                super.mouseClicked(e);
            }
        });

        MouseAdapter itemMouseListener = new MouseAdapter()
        {
            @Override
            public void mouseEntered(MouseEvent e)
            {
                JLabel source = ((JLabel) e.getSource());
                if (source != selectedLabel)
                {
                    source.setBackground(Colors.ITEM_SELECTED_LIGHT);
                }
                super.mouseEntered(e);
            }

            @Override
            public void mouseExited(MouseEvent e)
            {
                JLabel source = ((JLabel) e.getSource());
                if (source != selectedLabel)
                {
                    source.setBackground(Colors.WINDOW_BACKGROUND);
                }
                super.mouseExited(e);
            }

            @Override
            public void mouseClicked(MouseEvent e)
            {
                JLabel source = ((JLabel) e.getSource());

                if (source != selectedLabel)
                {
                    selectedLabel(source);

                    if (source.getText().equals("????????????"))
                    {
                        cardLayout.show(settingAreaPanel, CHANGE_AVATAR);
                    }
                    else if (source.getText().equals("????????????"))
                    {
                        cardLayout.show(settingAreaPanel, CHANGE_PASSWORD);
                    }
                    else if (source.getText().equals("???"))
                    {
                        cardLayout.show(settingAreaPanel, ME);
                    }
                    else if (source.getText().equals("??????"))
                    {
                        cardLayout.show(settingAreaPanel, ABOUT);
                    }
                    else if (source.getText().equals("????????????"))
                    {
                        cardLayout.show(settingAreaPanel, CLEAR_CHACE);
                    }
                }


                super.mouseClicked(e);
            }
        };

        changeAvatarLabel.addMouseListener(itemMouseListener);
        changePasswordLabel.addMouseListener(itemMouseListener);
        meLabel.addMouseListener(itemMouseListener);
        aboutLabel.addMouseListener(itemMouseListener);
        clearCacheLabel.addMouseListener(itemMouseListener);
    }

    private void selectedLabel(JLabel label)
    {
        selectedLabel = label;

        for (Component component : settingMenuPanel.getComponents())
        {
            component.setBackground(Colors.WINDOW_BACKGROUND);
        }

        label.setBackground(Colors.SCROLL_BAR_TRACK_LIGHT);
    }


    public static SystemConfigDialog getContext()
    {
        return context;
    }

    private void processButtonLabel(JLabel label)
    {
        label.setFont(FontUtil.getDefaultFont(13));
        label.setForeground(Colors.DARKER);
        label.setBorder(new RCBorder(RCBorder.BOTTOM, Colors.SHADOW));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(50, 30));
        label.setCursor(handCursor);
        label.setOpaque(true);
    }
}
